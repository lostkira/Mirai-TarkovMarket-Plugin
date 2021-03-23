import net.mamoe.mirai.alsoLogin
import net.mamoe.mirai.console.MiraiConsole
import net.mamoe.mirai.console.plugin.PluginManager.INSTANCE.enable
import net.mamoe.mirai.console.terminal.MiraiConsoleTerminalLoader

suspend fun main(){
    MiraiConsoleTerminalLoader.startAsDaemon()

    TarkovPluginMain.enable()

    val bot = MiraiConsole.addBot(3108655379, "Ekcgok8Mg29FvBaz").alsoLogin()


    MiraiConsole.job.join()
}