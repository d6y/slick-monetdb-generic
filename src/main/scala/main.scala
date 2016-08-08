
import scala.concurrent.Await
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

trait MonetDbDriver extends slick.driver.JdbcDriver with slick.driver.PostgresDriver {
}

object MonetDbDriver extends MonetDbDriver

import MonetDbDriver.api._

object Example extends App {

  final case class Voyage(
    number           : Long,
    boatname         : Option[String],
    master           : Option[String],
    tonnage          : Option[Int],
    departureHarbour : Option[String]
  )


  final class VoyageTable(tag: Tag) extends Table[Voyage](tag, "voyages") {

    def number           = column[Long]("number")
    def boatname         = column[Option[String]]("boatname")
    def master           = column[Option[String]]("master")
    def tonnage          = column[Option[Int]]("tonnage")
    def departureHarbour = column[Option[String]]("departure_harbour")

    def * = (number, boatname, master, tonnage, departureHarbour) <> (Voyage.tupled, Voyage.unapply)
  }

  lazy val voyages = TableQuery[VoyageTable]

  val db = Database.forConfig("voc")

  val query = voyages.filter(_.departureHarbour === "Ceylon").take(5)

  Await.result(db.run(query.result), 2 seconds).foreach(println)

}
