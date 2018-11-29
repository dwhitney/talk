import cats._
import cats.implicits._

object EitherExample extends App{

  def registerUser(userInfo: UserInfo): Either[Throwable,User] = Right(User())
  
  def createBasket(user: User): Either[Throwable,Basket] = Right(Basket())
 
  def addItemToBasket(item: Item, basket: Basket): Either[Throwable,Basket] = Right(Basket())
  
  def purchaseBasket(user: User, basket: Basket): Either[Throwable,Receipt] = Right(Receipt())
  
  def quickCheckout(item: Item, userInfo: UserInfo): Either[Throwable,Receipt] = for{
    user        <- registerUser(userInfo)
    basket      <- createBasket(user)
    fullBasket  <- addItemToBasket(item, basket)
    receipt     <- purchaseBasket(user, fullBasket)
  } yield receipt

  val result: Either[Throwable,Receipt] = quickCheckout(Item(), UserInfo())

  println(result)

}
