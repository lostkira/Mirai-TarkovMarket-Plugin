import net.mamoe.mirai.alsoLogin
import net.mamoe.mirai.console.MiraiConsole
import net.mamoe.mirai.console.plugin.PluginManager.INSTANCE.enable
import net.mamoe.mirai.console.terminal.MiraiConsoleTerminalLoader
import net.mamoe.mirai.console.util.ConsoleExperimentalApi

@ConsoleExperimentalApi
suspend fun main(){
    MiraiConsoleTerminalLoader.startAsDaemon()

    TarkovPluginMain.enable()

    val bot = MiraiConsole.addBot(123456, "abcdef").alsoLogin()

    MiraiConsole.job.join()
}