import net.mamoe.mirai.console.command.CommandManager.INSTANCE.register
import net.mamoe.mirai.console.command.CommandManager.INSTANCE.unregister
import net.mamoe.mirai.console.command.CommandSender
import net.mamoe.mirai.console.command.CompositeCommand
import net.mamoe.mirai.console.command.SimpleCommand
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin

@net.mamoe.mirai.console.util.ConsoleExperimentalApi
object TarkovPluginMain : KotlinPlugin(
    JvmPluginDescription.loadFromResource()
) {
    override fun onEnable() {
        super.onEnable()
        logger.info("Tarkov plugin loaded")

        serverStatus.register()
        help.register()
        eft.register()
    }

    override fun onDisable() {
        super.onDisable()
        serverStatus.unregister()
        help.unregister()
        eft.unregister()
    }

    object serverStatus : SimpleCommand(
        TarkovPluginMain, "stat",
        description = "服务器状态检查",
    ){
        @Handler
        suspend fun CommandSender.handler(){
            sendMessage("Bot正常响应\n作者: EzrealC")
        }
    }

    object help : SimpleCommand(
        TarkovPluginMain, "?",
        description = "帮助"
    ){
        @Handler
        suspend fun CommandSender.handler(){
            sendMessage("/stat 检查服务器状态\n/eft search 物品名关键字 检索物品价格")
        }
    }

    object eft : CompositeCommand(
        TarkovPluginMain, "eft"
    ){
        @SubCommand
        suspend fun CommandSender.search(keyword:String){
            sendMessage("正在查询中...")
            val tempString = StringBuilder()
            try {
                val searchResult = ItemInfo(keyword, "cn")
                if (searchResult.itemBeanList.size != 0){
                    for (index in searchResult.itemBeanList.indices){
                        tempString.appendLine("物品名：" + searchResult.getNameByIndex(index))
                        tempString.appendLine("市场价格：" + searchResult.getPriceByIndex(index) + searchResult.getTraderPriceCurByIndex(index))
                        tempString.appendLine("商人收购价格：" + searchResult.getTraderNameByIndex(index)+ " " +searchResult.getTraderPriceByIndex(index) +
                                searchResult.getTraderPriceCurByIndex(index))
                        tempString.append("\n")
                    }
                    tempString.deleteAt(tempString.length - 1)
                    tempString.deleteAt(tempString.length - 1)
                }else{
                    tempString.append("!没有搜索到相关物品!")
                }
            } catch (e: Exception) {
                tempString.append("！请求出错了，请再试一次吧～")
            }
            sendMessage(String(tempString))
        }
    }
}