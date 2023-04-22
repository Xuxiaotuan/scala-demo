package interview.lru

import scala.collection.mutable

/**
 * @author : XuJiaWei
 * @since : 2023-04-22 12:08
 */

class LRUCache[K, V](capacity: Int) {
  val cache = mutable.LinkedHashMap.empty[K, V]
  val queue = new DoublyLinkedList[K]

  def get(key: K): Option[V] = {
    cache.get(key) match {
      case Some(value) =>
        queue.moveToFront(key)
        Some(value)
      case None => None
    }
  }

  def put(key: K, value: V): Unit = {
    if (cache.contains(key)) {
      queue.moveToFront(key)
    } else {
      if (cache.size >= capacity) {
        val last = queue.removeLast()
        cache.remove(last)
      }
      queue.addToFront(key)
    }
    cache.put(key, value)
  }
}

class DoublyLinkedList[K] {
  private case class Node(key: K, var prev: Node, var next: Node)

  private val head = Node(null.asInstanceOf[K], null, null)
  private val tail = Node(null.asInstanceOf[K], head, null)
  head.next = tail

  private val map = scala.collection.mutable.Map.empty[K, Node]

  def addToFront(key: K): Unit = {
    val node = new Node(key, head, head.next)
    head.next.prev = node
    head.next = node
    map.put(key, node)
  }

  def remove(node: Node): Unit = {
    node.prev.next = node.next
    node.next.prev = node.prev
    map.remove(node.key)
  }

  def removeLast(): K = {
    val last = tail.prev
    remove(last)
    last.key
  }

  def moveToFront(key: K): Unit = {
    map.get(key) match {
      case Some(node) =>
        remove(node)
        addToFront(key)
      case None =>
    }
  }
}