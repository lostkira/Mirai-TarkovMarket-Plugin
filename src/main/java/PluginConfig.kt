import net.mamoe.mirai.console.data.AutoSavePluginConfig
import net.mamoe.mirai.console.data.ValueDescription
import net.mamoe.mirai.console.data.value

object PluginConfig : AutoSavePluginConfig("config"){
    @ValueDescription("tarkov-market.com提供的API Key")
    var apiKey: String by value("")
}