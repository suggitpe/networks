package org.suggs.networking.sockets

import java.net.{Socket, InetAddress}
import grizzled.slf4j.Logger
import java.io.{ObjectInputStream, DataInputStream, DataOutputStream, ObjectOutputStream}

/**
 * TODO: Justify why you have written this class!
 * To change this template use File | Settings | File Templates.
 */
object Client {

  val LOG = Logger(getClass)

  // 1.  create a socket
  // 5. connect
  // 7. send request
  // 8. receive
  // 10. disconnect / close

  def main(args: Array[String]) {

    val objectToSend = "12345687"

    val address = InetAddress.getByName("localhost")
    LOG.debug("Connecting to the socket")
    val socket = new Socket(address, 9999)
    val outStream = new ObjectOutputStream(new DataOutputStream(socket.getOutputStream))
    val inStream = new ObjectInputStream(new DataInputStream(socket.getInputStream))

    LOG.debug("Sending object")
    outStream.writeObject(objectToSend)
    outStream.flush

    LOG.debug("Sent object to server")

    LOG.debug("Waiting for response ...")

    val in = inStream.readObject
    LOG.debug("Read in object " + in)

    outStream.close
    inStream.close
    socket.close
  }


}
