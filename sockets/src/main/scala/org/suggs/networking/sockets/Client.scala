package org.suggs.networking.sockets

import java.net.InetAddress
import grizzled.slf4j.Logger

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


    val address = InetAddress.getByName("localhost")

    LOG.debug("Foo bar")
  }


}
