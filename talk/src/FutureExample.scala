import cats._
import cats.implicits._

import scala.concurrent.duration._
import scala.concurrent.{Await, ExecutionContext, Future}

object FutureExample extends App{

  implicit val ec = ExecutionContext.global

  def registerUser(userInfo: UserInfo): Future[User] = Future.successful(User())
  
  def createBasket(user: User): Future[Basket] = Future.successful(Basket())
 
  def addItemToBasket(item: Item, basket: Basket): Future[Basket] = Future.successful(Basket())
  
  def purchaseBasket(user: User, basket: Basket): Future[Receipt] = Future.successful(Receipt())
  
  def quickCheckout(item: Item, userInfo: UserInfo): Future[Receipt] = for{
    user        <- registerUser(userInfo)
    basket      <- createBasket(user)
    fullBasket  <- addItemToBasket(item, basket)
    receipt     <- purchaseBasket(user, fullBasket)
  } yield receipt

  val result: Future[Receipt] = quickCheckout(Item(), UserInfo())

  println(Await.result(result, 1.seconds))

}
