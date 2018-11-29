import cats._
import cats.implicits._

case class UserInfo()
case class User()
case class Item()
case class Receipt()
case class Basket()

object OptionExample extends App{

  def registerUser(userInfo: UserInfo): Option[User] = Some(User())
  
  def createBasket(user: User): Option[Basket] = Some(Basket())
 
  def addItemToBasket(item: Item, basket: Basket): Option[Basket] = Some(Basket())
  
  def purchaseBasket(user: User, basket: Basket): Option[Receipt] = Some(Receipt())
  
  def quickCheckout(item: Item, userInfo: UserInfo): Option[Receipt] = for{
    user        <- registerUser(userInfo)
    basket      <- createBasket(user)
    fullBasket  <- addItemToBasket(item, basket)
    receipt     <- purchaseBasket(user, fullBasket)
  } yield receipt

  val result: Option[Receipt] = quickCheckout(Item(), UserInfo())

  println(result)

}
