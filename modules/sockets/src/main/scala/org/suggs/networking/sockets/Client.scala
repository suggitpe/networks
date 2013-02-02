package org.suggs.networking.sockets

import java.net.{Socket, InetAddress}
import grizzled.slf4j.Logger
import java.io.{ObjectInputStream, DataInputStream, DataOutputStream, ObjectOutputStream}

/**
 * Simple client application that will push a message to the server and wait
 * for it to be echo'd back.
 */
object Client {

  val LOG = Logger(getClass)
  val objectToSend = "12345687"

  def main(args: Array[String]) {
    val socket = createSocket
    sendMessageToServer(socket, objectToSend)
    waitForMessageFromServer(socket)
  }

  def createSocket(): Socket = {
    new Socket(InetAddress.getByName("localhost"), 9999)
  }

  def sendMessageToServer(socket: Socket, thing: Object) = {
    val outStream = new ObjectOutputStream(new DataOutputStream(socket.getOutputStream))
    LOG.debug("Sending object [" + thing + "] to the server")
    outStream.writeObject(thing)
    outStream.flush
    LOG.debug("Object sent to server")
  }

  def waitForMessageFromServer(socket: Socket) = {
    LOG.debug("Waiting for message from the server")
    val inStream = new ObjectInputStream(new DataInputStream(socket.getInputStream))
    val in = inStream.readObject
    LOG.debug("Received object from server [" + in + "]")
    inStream.close
    socket.close
  }


}
