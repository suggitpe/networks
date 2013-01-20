package org.suggs.networking.sockets

import grizzled.slf4j.Logger
import java.net.{Socket, ServerSocket}
import java.io.{DataInputStream, ObjectInputStream}

/**
 * TODO: Justify why you have written this class!
 * To change this template use File | Settings | File Templates.
 */
object Server {

  val LOG = Logger(getClass)

  // 1. create socket
  // 2. bind and
  //
  // 3. listen to a port
  // 4. accept requests
  // 6. receive
  // 9. send
  // 10.close / discomment

  def main(args: Array[String]) {
    val listener = new ServerSocket(9999)
    while (true) {
      LOG.debug("Starting new listsner thread")
      new ServerThread(listener.accept).start()
    }
    listener.close


  }

  case class ServerThread(socket: Socket) extends Thread("ServerThread") {
    override def run(): Unit = {
      val outStream = socket.getOutputStream
      val inStream = new ObjectInputStream(new DataInputStream(socket.getInputStream))

      LOG.debug("Reading object")
      val objectReceived = inStream.readObject
      LOG.debug("Received object " + objectReceived)


    }

  }

}
