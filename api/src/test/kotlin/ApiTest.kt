import com.google.gson.Gson
import com.vitoling.cloudmusictv.data.service.CloudMusicService.fetchMyPlaylists
import com.vitoling.cloudmusictv.data.service.CloudMusicService.fetchPlaylistDetail
import com.vitoling.cloudmusictv.data.service.CloudMusicService.fetchSongDetail
import com.vitoling.cloudmusictv.data.service.CloudMusicService.fetchSongUrl
import org.junit.jupiter.api.Test
import com.vitoling.cloudmusictv.data.service.CloudMusicService as service

/**
 * Created by lingzhiyuan.
 * Time : 20:11.
 * Description:
 *
 */

object Tests {

    private val GSON by lazy { Gson() }

    @Test
    fun loginViaCellphone() {
        val pd = service.loginViaCellphone("", "")
        println(GSON.toJson(pd.body()))
    }

    @Test
    fun loginViaUsername() {
        val pd = service.loginViaUsername("", "")
        println(GSON.toJson(pd.body()))
    }

    @Test
    fun myPlaylist() {
        val pd = fetchMyPlaylists(uid = 17952620)
        println(GSON.toJson(pd.body()))
    }

    @Test
    fun songDetail() {
        val pd = fetchSongDetail(id = 33111366)
        println(GSON.toJson(pd.body()))
    }

    @Test
    fun songUrl() {
        val pd = fetchSongUrl(id = 33111366)
        println(GSON.toJson(pd.body()))
    }

    @Test
    fun playlistDetail() {
        val pd = fetchPlaylistDetail(14660449, 0, true, 1000, 1000)
        println(GSON.toJson(pd.body()))
    }
}
