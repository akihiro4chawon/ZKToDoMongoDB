package com.github.akihiro4chawon.mongodb

import com.mongodb.DB
import com.mongodb.Mongo

object MongoDBManager {
  private val mongo = new Mongo
  
  def getDB(dbName: String) = synchronized {
    mongo.getDB(dbName)
  }
}
