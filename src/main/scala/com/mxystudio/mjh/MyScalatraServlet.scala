package com.mxystudio.mjh

import org.scalatra._
import scalate.ScalateSupport

class MyScalatraServlet extends MjhwebsiteStack with ScalateSupport {

  get("/index") {
    redirect("/")
  }
  get("/index.html") {
    redirect("/")
  }

  get("/") {
    contentType = "text/html"
    mustache("index.mustache", "layout" -> "")
  }

  get("/picshow") {
    contentType = "text/html"
    mustache("picshow.mustache", "layout" -> "")

  }

}
