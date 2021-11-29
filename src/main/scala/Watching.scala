import java.nio.file.StandardWatchEventKinds.{ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY}
import java.io.File
import java.io.IOException
import java.nio.file.ClosedWatchServiceException
import java.nio.file.FileSystems
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.WatchEvent
import java.nio.file.WatchKey
import java.nio.file.WatchService
import java.util.ArrayList
import java.util.Collections
import java.util.List
import java.util.function.Consumer;

object Watching extends App {

  val watchService = FileSystems.getDefault.newWatchService()
  Paths.get("C:\\Users\\snande3\\Documents\\Test").register(watchService, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY)

  while(true) {
    val key = watchService.take()
    val events = key.pollEvents()

    if(events.isEmpty){
      print("is empty")
    }
    else{
       events.forEach(event => print(event.kind()+" Name: "+event.context()))
    }
    key.reset()
  }

}
