package `fun`.fifu.yallage.`fast-furnace`

import `fun`.fifu.yallage.`fast-furnace`.command.FastFurnaceCommand
import `fun`.fifu.yallage.`fast-furnace`.listener.FurnaceListener
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitTask

class FastFurnace : JavaPlugin() {
    companion object {
        lateinit var plugin: FastFurnace
        lateinit var bigLoop: BukkitTask
    }

    override fun onLoad() {
        Configuring.loadConfig()
    }

    override fun onEnable() {
        plugin = this
        server.pluginManager.registerEvents(FurnaceListener(), this)
        Bukkit.getPluginCommand("fast-furnace")?.setExecutor(FastFurnaceCommand())
        logger.info("Fast Furnace plugin is Loaded. By:NekokeCore")
    }

    override fun onDisable() {
        bigLoop.cancel()
        logger.info("Fast Furnace plugin is Disabled. Thinks for use :)")
        FurnaceListener.fastFurnaceMap.forEach { (k, _) ->
            FurnaceListener.removeFastFurnace(k)
        }
    }
}