import com.google.gson.Gson
import com.vitoling.cloudmusictv.data.service.*

/**
 * Created by lingzhiyuan.
 * Time : 20:11.
 * Description:
 *
 */

fun main(args: Array<String>) {
    val res = CloudMusicService.loginViaUsername("vitoling95@163.com", "ling371148667")
    println(Gson().toJson(res.body()))
    //    val res = CloudMusicService.loginViaCellphone("18970967366", "ling371148667")
    //    println(Gson().toJson(res.body()))
    //    val pl = CloudMusicService.fetchMyPlaylist(uid = res.body().account?.id ?: 48167)
    //    println(Gson().toJson(pl.body()))
    //    val pd = CloudMusicService.fetchPlaylistDetail(/*pl.body().playlist?.get(5)?.id ?: */14660449, 0, true, 1000, 1000)
    //    println(Gson().toJson(pd.body()))
    //    val sd = CloudMusicService.fetchSongDetail(pd.body().playlist.trackIds[0].id)
    //    println(Gson().toJson(sd.body()))
    //    val su = CloudMusicService.fetchSongUrl(sd.body().songs[0].id)
    //    println(Gson().toJson(su.body()))
}

