import cats._
import cats.implicits._

import scala.concurrent.{Await, ExecutionContext, Future}
import scala.concurrent.duration._

object FExample extends App {
  
  implicit val ec = ExecutionContext.global

  type Throwing[F[_]] = MonadError[F, Throwable]

  def registerUser[F[_] : Applicative](userInfo: UserInfo): F[User] = Applicative[F].pure(User())
  
  def createBasket[F[_] : Applicative](user: User): F[Basket] = Applicative[F].pure(Basket())
 
  def addItemToBasket[F[_] : Applicative](item: Item, basket: Basket): F[Basket] = Applicative[F].pure(Basket())
  
  def purchaseBasket[F[_] : Applicative](user: User, basket: Basket): F[Receipt] = Applicative[F].pure(Receipt())
  
  def quickCheckout[F[_] : Monad](item: Item, userInfo: UserInfo): F[Receipt] = for {
    user        <- registerUser[F](userInfo)
    basket      <- createBasket[F](user)
    fullBasket  <- addItemToBasket[F](item, basket)
    receipt     <- purchaseBasket[F](user, fullBasket)
  } yield receipt






  
  val optionResult: Option[Receipt] = quickCheckout[Option](Item(), UserInfo()) 
  println(optionResult)

  val eitherResult: Either[Throwable,Receipt] = quickCheckout[Either[Throwable,?]](Item(), UserInfo())
  println(eitherResult)

  val futureResult: Future[Receipt] = quickCheckout[Future](Item(), UserInfo()) 
  println(Await.result(futureResult, 1.seconds))



}
