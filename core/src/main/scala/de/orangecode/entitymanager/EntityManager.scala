package de.orangecode.entitymanager

import java.io.{RandomAccessFile, FileWriter, BufferedWriter, PrintWriter}
import java.nio.ByteBuffer
import java.nio.channels.FileChannel
import java.nio.file.{Files, Paths}

import com.typesafe.scalalogging.LazyLogging
import de.orangecode.Context
import isat.model.Vertex
import org.apache.commons.io.FileUtils
import org.apache.spark
import org.apache.spark.Logging
import org.apache.spark.rdd.RDD
import org.apache.spark.storage.StorageLevel

import scala.io.Source
import scala.reflect.ClassTag

/**
 * @author Tarek Auel
 * @since June 06, 2015.
 */
abstract class EntityManager[T <: Vertex: ClassTag](ctx: Context, convert: String => T)
  extends LazyLogging {

  protected val filename: String
  protected val typename: String

  private[this] lazy val dataPath: String = if (System.getenv("dataPath") != null) System.getenv("dataPath") + "/" else "" + typename + ".json"

  protected var allEntities: Option[RDD[(Long, T)]] = None
  protected var changed = false

  def getAll: RDD[T] = {
    getAllPair.map(_._2)
  }

  private def getAllPair: RDD[(Long, T)] = {
    allEntities getOrElse {
      /*val dirOut = Paths.get(filename + ".out.json")
      if (dirOut.toFile.exists()) {
        if (Paths.get(filename + ".in.json").toFile.exists()) {
          removeAll(filename + ".in.json")
        }
        FileUtils.copyDirectory(dirOut.toFile, Paths.get(filename + ".in.json").toFile)
        val e = ctx.sc.textFile(filename + ".in.json").map(convert).distinct()
      /*val dirOut = Paths.get(filename + ".out.object")
      if (dirOut.toFile.exists()) {
        if (Paths.get(filename + ".in.object").toFile.exists()) {
          removeAll(filename + ".in.object")
        }
        FileUtils.copyDirectory(dirOut.toFile, Paths.get(filename + ".in.object").toFile)
        val e = ctx.sc.objectFile[T](filename + ".in.object")*/
        e.persist(StorageLevel.MEMORY_AND_DISK)
        allEntities = Some(e.map(x => (x.getId, x)))
        e.repartition(6)
        allEntities.get
      /*logger.info("Start loading " + dataPath)
      val strings = new String(Files.readAllBytes(Paths.get(dataPath))).split("\n")
      if (strings.nonEmpty) {
        val entities = strings.dropRight(1).map(s =>
          if (s.charAt(0) == '{') s.substring(0, s.length - 1) else s.substring(1, s.length - 1)
        ).map(convert)

        val entityRdd = ctx.sc.parallelize(entities)
        allEntities = Some(entityRdd.map(x => (x.getId, x)))
        addEntity(entityRdd)
        allEntities.get*/
      } else {*/
        ctx.sc.parallelize(Seq[(Long, T)]())
      //}
    }
  }

  def updateEntity(list: Seq[T]): Seq[T] = {
    this.synchronized {
      val rddList = ctx.sc.parallelize(list map (x => (x.getId, x)))
      val remainingOld = getAllPair subtractByKey rddList
      allEntities = Some(remainingOld ++ rddList)
      changed = true
      list
    }
  }

  def addEntity(list: Seq[T]): RDD[T] = {
    addEntity(ctx.sc.parallelize(list))
  }

  def addEntity(rdd: RDD[T]): RDD[T] = {
    this.synchronized {
      val all = getAllPair
      val newE = rdd.map(x => (x.getId, x)).subtractByKey(all).cache()
      if (!newE.isEmpty()) {
        //logger.info(s"${newE.count()} entities has been added")
        allEntities = Some(all ++ newE)
        changed = true
        newE.map(_._2)
      } else {
        ctx.sc.parallelize(List[T]())
      }
    }
  }

  def persist(): Unit = {
    if (changed) {
      this.synchronized {
        logger.info("Start saving: " + dataPath)
//        getAll.persist()
        //removeAll("/Users/tarek/IdeaProjects/TwitterConsumer/" + filename + ".2.out.json")
        //moveAll("/Users/tarek/IdeaProjects/TwitterConsumer/" + filename + ".out.json", "/Users/tarek/IdeaProjects/TwitterConsumer/" + filename + ".2.out.json")
        //getAll.map(_.getJson).saveAsTextFile(filename + ".out.json")
        /*removeAll(filename + ".out.object")
        getAll.saveAsObjectFile(filename + ".out.object")
        changed = false*/
        /*val toSave = getAll.distinct()
        if (!toSave.isEmpty()) {
          val fw = new FileWriter(dataPath)
          toSave.collect().foreach(t => fw.write(t.getJson + ",\n"))
          fw.close()
        }*/
        logger.info("Finished saving: " + dataPath)
      }
    } else {
      logger.info("Everything up-to-date.")
    }
  }

  private[this] def moveAll(src: String, dst: String): Unit = {
    val f = Paths.get(src).toFile
    if (f.exists()) {
      removeAll(dst)
      Files.move(Paths.get(src), Paths.get(dst))
    }
  }

  private[this] def removeAll(path: String): Unit = {
    val p = Paths.get(path)
    if (p.toFile.isDirectory) {
      val files = p.toFile.listFiles()
      files.foreach((f) => removeAll(f.getAbsolutePath))
    }
    if (p.toFile.exists()) Files.delete(p)
  }

}
