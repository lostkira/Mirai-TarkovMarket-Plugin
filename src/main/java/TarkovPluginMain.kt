import com.dtflys.forest.exceptions.ForestNetworkException
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

        ServerStatus.register()
        Help.register()
        EFT.register()

        PluginConfig.reload()

        logger.info("Tarkov Market plugin loaded")

        if (PluginConfig.apiKey.length != 16){
            logger.warning("API Key可能没有正确配置，请检查配置文件")
        }
    }

    override fun onDisable() {
        super.onDisable()
        ServerStatus.unregister()
        Help.unregister()
        EFT.unregister()
    }

    object ServerStatus : SimpleCommand(
        TarkovPluginMain, "stat",
        description = "Bot存活检查",
    ) {
        @Handler
        suspend fun CommandSender.handler() {
            sendMessage("Bot还活着\n作者: EzrealC")
        }
    }

    object Help : SimpleCommand(
        TarkovPluginMain, "?",
        description = "帮助"
    ) {
        @Handler
        suspend fun CommandSender.handler() {
            sendMessage("/stat 检查服务器状态\n/eft search 物品名关键字 检索物品价格")
        }
    }

    object EFT : CompositeCommand(
        TarkovPluginMain, "eft"
    ) {
        @SubCommand
        suspend fun CommandSender.search(keyword: String) {
            if (PluginConfig.apiKey.length == 16) {
                sendMessage("正在查询中...")
                val tempString = StringBuilder()
                try {
                    val searchResult = ItemInfo(PluginConfig.apiKey, keyword, "cn")
                    if (searchResult.itemBeanList.size != 0) {
                        for (index in searchResult.itemBeanList.indices) {
                            tempString.appendLine("物品名：" + searchResult.getNameByIndex(index))
                            tempString.appendLine("市场价格：" + searchResult.getPriceByIndex(index) + searchResult.getTraderPriceCurByIndex(index))
                            tempString.appendLine(
                                "商人收购价格：" + searchResult.getTraderNameByIndex(index) + " " + searchResult.getTraderPriceByIndex(index) +
                                        searchResult.getTraderPriceCurByIndex(index)
                            )
                            tempString.append("\n")
                        }
                        tempString.deleteRange(tempString.length - 2, tempString.length)
                    } else {
                        tempString.append("!没有搜索到相关物品!")
                    }
                } catch (e : ForestNetworkException){
                    tempString.appendLine("请求出错啦！")
                    tempString.appendLine("状态码: ${e.statusCode}")
                    tempString.append(e.response.content)
                }catch (e: Exception) {
                    tempString.append("！请求出错了，请再试一次吧～")
                }
                sendMessage(String(tempString))
            } else {
                logger.error("API Key设置错误")
                sendMessage("!API Key设置错误，请联系管理员!")
            }
        }
    }
}
