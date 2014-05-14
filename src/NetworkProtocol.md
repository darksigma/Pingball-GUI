Network Protocol
================

Client to Server
----------------

### Summary of Messages

BallMessage -- Sends a ball from one board to another that are connected by a wall

PortalBallMessage -- Sends a ball from one portal to another portal that are on different boards

MyPortalMessage -- Sends a hello message to the server when a client joins that provides the board's name and the names of all the portals it contains

### Informal Grammar

BallMessage ::= 'ball' SPACE BallName SPACE Wall SPACE xPosition SPACE yPosition SPACE xVelocity SPACE yVelocity

PortalBallMessage ::= 'portalball' SPACE BallName SPACE BoardName SPACE PortalName SPACE xPosition SPACE yPosition SPACE xVelocity SPACE yVelocity

MyPortalMessage ::= 'myportals' SPACE BoardName (SPACE PortalName)*

Wall = 'left' | 'right' | 'top' | 'bottom'

Server to Client
----------------

### Summary of Messages

ConnectMessage -- Connect the wall of the recipient to another board

DisconnectMessage -- Disconnect the wall of the recipient from another board

BallMessage -- Receive a ball from another board via passing through a wall

KeyUpMessage -- Lets the client know a key was released after being pressed

KeyDownMessage -- Lets the client know how a key was pushed down

MyPortalMessage -- Lets the receipient know what other boards/portals are on the server so that the appropriate portals can be activated

BoardNotOnServerMessage -- Lets the board know that a client has been disconnected from the server so that the appropriate portals can be deactivated

PortalBallMessage -- Passes a ball to a portal from another portal

PortalSelfOnlyMessage -- Sent before disconnection to ensure that the appropriate portals are inactivated. 

### Informal Grammar

ConnectMessage ::= 'connect' SPACE Wall SPACE BoardName

DisconnectMessage ::= 'disconnect' SPACE Wall

BallMessage ::= 'ball' SPACE BallName SPACE Wall SPACE xPosition SPACE yPosition SPACE xVelocity SPACE yVelocity

KeyUpMessage ::= 'keyup' SPACE Key

KeyDownMessage ::= 'keydown' SPACE Key

MyPortalMessage ::= 'myportals' SPACE BoardName (SPACE PortalName)*

BoardNotOnServerMessage ::= 'notonboard' SPACE BoardNAme

PortalBallMessage ::= 'portalball' SPACE BallName SPACE BoardName SPACE PortalName SPACE xPosition SPACE yPosition SPACE xVelocity SPACE yVelocity

PortalSelfOnlyMessage ::= 'portalSelfOnly'

Wall = 'left' | 'right' | 'top' | 'bottom'

Client to GUI
-------------

