board name=ExampleB friction2 = 0.015

# define a ball
ball name=BallA x=0.9 y=3.1 xVelocity=10.4 yVelocity=10.3 


# define the bumpers

squareBumper name= SquareA x =3  y= 10
triangleBumper name = Tri1 x =4 y =12 orientation=0
triangleBumper name=Tri2 x=4 y=11 orientation =  90
circleBumper name= CircleA x = 10 y = 3

#define the flippers
rightFlipper name=FlipR1 x=1 y=4 orientation=0
leftFlipper name=FlipL1 x=3 y=3 orientation=0

#define the absorber
absorber name=Abs x=3 y=14 width=1 height=1 