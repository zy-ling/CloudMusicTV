import com.google.gson.Gson
import com.vitoling.cloudmusictv.data.service.*
import org.junit.jupiter.api.Test

/**
 * Created by lingzhiyuan.
 * Time : 20:11.
 * Description:
 *
 */

class Tests {

    companion object {
        private val gson by lazy { Gson() }
    }

    @Test
    fun loginViaCellphone() {
        val pd = CloudMusicService.loginViaCellphone("", "")
        println(gson.toJson(pd.body()))
    }

    @Test
    fun loginViaUsername() {
        val pd = CloudMusicService.loginViaUsername("", "")
        println(gson.toJson(pd.body()))
    }

    @Test
    fun fetchMyPlaylists() {
        val pd = CloudMusicService.fetchMyPlaylists(uid = 17952620)
        println(gson.toJson(pd.body()))
    }

    @Test
    fun fetchSongDetail() {
        val pd = CloudMusicService.fetchSongDetail(id = 33111366)
        println(gson.toJson(pd.body()))
    }

    @Test
    fun fetchSongUrl() {
        val pd = CloudMusicService.fetchSongUrl(id = 33111366)
        println(gson.toJson(pd.body()))
    }

    @Test
    fun fetchPlalistDetail() {
        val pd = CloudMusicService.fetchPlaylistDetail(14660449, 0, true, 1000, 1000)
        println(gson.toJson(pd.body()))
    }
}
