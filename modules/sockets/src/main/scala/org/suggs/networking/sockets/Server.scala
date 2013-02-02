package org.suggs.networking.sockets

import grizzled.slf4j.Logger
import java.net.{Socket, ServerSocket}
import java.io.{ObjectOutputStream, DataOutputStream, DataInputStream, ObjectInputStream}

/**
 * TODO: Justify why you have written this class!
 * To change this template use File | Settings | File Templates.
 */
object Server {

  val LOG = Logger(getClass)


  def main(args: Array[String]) {
    val socket = new ServerSocket(9999)
    while (true) {
      LOG.debug("Starting new listener thread")
      new ServerThread(socket.accept).start()
    }
    socket.close
  }

  case class ServerThread(socket: Socket) extends Thread("ServerThread") {


    override def run() = {
      returnObject(socket, recieveObject(socket))
    }

    def recieveObject(socket: Socket): Object = {
      val inStream = new ObjectInputStream(new DataInputStream(socket.getInputStream))
      LOG.debug("waiting for object from stream")
      inStream.readObject
    }

    def returnObject(socket: Socket, objectReceived: Object) = {
      LOG.debug("Received object [" + objectReceived + "] from stream")
      val outStream = new ObjectOutputStream(new DataOutputStream(socket.getOutputStream))
      LOG.debug("Sending object back over the stream")
      outStream.writeObject(objectReceived)
      outStream.close
      socket.close
    }

  }

}
