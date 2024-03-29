package isat.streaming

import twitter4j.FilterQuery

/**
 * @author Tarek Auel
 * @since June 21, 2015.
 */
object Query {

  def getQuery: FilterQuery = {
    new FilterQuery().track(handles ++ hashTags).language(Array("en"))
  }
  
  private[this] val handles = Array(
    "@IBMWatson",
    "@MetaMindIO",
    "@Numenta",
    "@ai_one",
    "@MSFTResearch",
    "@NaraLogics",
    "@ScaledInference",
    "@vicariousinc",
    "@visionfactoryAI",
    "@googleresearch",
    "@deeplearning4j",
    "@BaiduResearch",
    "@ersatzlabs",
    "@RapidMiner",
    "@ContextRelevant",
    "@h2oai",
    "@LiftIgniter",
    "@Azure",
    "@datoinc",
    "@YhatHQ",
    "@AlpineDataLabs",
    "@DataRPM",
    "@sparkbeyond",
    "@wiseio",
    "@nutonian",
    "@cortical_io",
    "@idibon",
    "@LuminosoInsight",
    "@WitNL",
    "@MaluubaInc",
    "@Mind_Ops",
    "@bigmlcom",
    "@indicoData",
    "@algorithmia",
    "@expectlabs",
    "@PredictionIO",
    "@ClarifaiInc",
    "@dextro_co",
    "@ViSenze",
    "@gridspaceinc",
    "@PopUpArchive",
    "@NuanceEnt",
    "@preact",
    "@AvisoInc",
    "@relateiq",
    "@ngdata_com",
    "@Clarabridge",
    "@framedio",
    "@InferInc",
    "@attensity",
    "@causata",
    "@CrossmatchTech",
    "@ConjurInc",
    "@eyeverify",
    "@cylanceinc",
    "@Area1Security",
    "@BitSight",
    "@bionym",
    "@siftscience",
    "@socureme",
    "@ThreatMetrix",
    "@feedzai",
    "@brighterion",
    "@Verafin",
    "@TalentBinHiring",
    "@Entelo",
    "@getPredikt",
    "@Connectifier",
    "@gild",
    "@hiqlabs",
    "@BrightFunnel",
    "@bloomreachinc",
    "@CommandIQ",
    "@AirPR",
    "@radius",
    "@tellapart",
    "@PeoplePattern",
    "@freshplum",
    "@RobinLabs",
    "@kasistoinc",
    "@fusemachines",
    "@clara_labs",
    "@adataoinc",
    "@PalantirTech",
    "@Quid",
    "@dreasoning",
    "@FirstRain",
    "@metamarkets",
    "@Dstillery",
    "@gorocketfuel",
    "@Yieldmo",
    "@AdbrainTech",
    "@BlueRiverTech",
    "@TerrAvionCorp",
    "@HoneyCombCorp",
    "@climatecorp",
    "@tuletech",
    "@mavrx_inc",
    "@declara",
    "@coursera",
    "@Knewton",
    "@Kidaptive",
    "@FinGeniusAI",
    "@AlphaSenseInc",
    "@Kensho",
    "@Dataminr",
    "@MinettaBrook",
    "@orbitalinsight",
    "@Binatix",
    "@LexMachina",
    "@brightleaf",
    "@Counselytics",
    "@ravellaw",
    "@eBrevia",
    "@KiraSystems",
    "@sightmachine",
    "@Microscan",
    "@IVISYS_",
    "@boulderimaging",
    "@Parzivalinc",
    "@transcriptic",
    "@zephyrhealth",
    "@GrandRoundTable",
    "@binaTechs",
    "@TuteGenomics",
    "@kaggle",
    "@Ayasdi",
    "@TachyusCorp",
    "@fluturads",
    "@Outbrain",
    "@ArriaNLG",
    "@sailthru",
    "@owlin",
    "@narrativesci",
    "@yseopAI",
    "@Prismatic",
    "@AInsights",
    "@Affirm",
    "@InVenture",
    "@ZestFinance",
    "@BillGuard",
    "@LendUpLoans",
    "@LendingClub",
    "@KabbageInc",
    "@DataKind",
    "@thorn",
    "@DataGuild",
    "@GoogleAuto",
    "@CypherITS",
    "@Mobileye",
    "@enlitic",
    "@3ScanTweets",
    "@Lumiata",
    "@Entopsis",
    "@BaySensors",
    "@Prism",
    "@celect",
    "@EuclidAnalytics"
  )

  private[this] val hashTags = Array(
    "#bigdata",
    "#datascience",
    "#machinelearning",
    "#analytics",
    "#python",
    "#startup",
    "#iot",
    "#deeplearning",
    "#ai",
    "#customersuccess",
    "#data",
    "#strataconf",
    "#rstats",
    "#custserv",
    "#webinar",
    "#custexp",
    "#hadoop",
    "#tweetsfromthearchive",
    "#h2oworld",
    "#nlproc",
    "#df14",
    "#sas13",
    "#predictiveanalytics",
    "#rapidminer",
    "#rmworld",
    "#api",
    "#nlp",
    "#opensource",
    "#cloud",
    "#customerchurn",
    "#h2o",
    "#internetofthings",
    "#marketing",
    "#spark",
    "#stratahadoop",
    "#business",
    "#artificialintelligence",
    "#5minuteswithingo",
    "#machineintelligence",
    "#dataviz"
  )

}
