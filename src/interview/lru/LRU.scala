package interview.lru

/**
 *
 * reference：https://medium.com/@goyalarchana17/scala-interview-series-implement-lru-cache-in-scala-d6a840ccb7a9
 *
 * @author : XuJiaWei
 * @since : 2023-04-22 12:07
 */
object LRU {

  def main(args: Array[String]): Unit = {
    val cache = new LRUCache[Int, Int](3)

    cache.put(1, 1)
    cache.put(2, 2)
    cache.put(3, 3)

    cache.get(1) // 返回 Some(1)
    cache.get(2) // 返回 Some(2)

    cache.put(4, 4) // 1 最久未使用,剔除

    cache.get(1) // 返回 None
    cache.get(3) // 返回 Some(3)

    cache.get(2)    // 返回 Some(2),并将 2 移到队首
    cache.put(5, 5) // 3 最久未使用,剔除

    cache.get(3) // 返回 None

    println(cache.cache) // Map(2 -> 2, 5 -> 5, 4 -> 4)
    println(cache.queue) // 2 ← 5 ← 4
  }

}
