package com.vitoling.cloudmusictv.data

import com.vitoling.cloudmusictv.data.common.enums.Gender
import java.time.LocalDateTime

/**
 * Created by lingzhiyuan.
 * Date : 2/25/17.
 * Time : 17:08.
 * Description:
 *
 */
object Me {

    var userId: Long = 0L
    var gender: Gender = Gender.FEMALE
    var creationTime: LocalDateTime = LocalDateTime.now()
    var updateTime: LocalDateTime = LocalDateTime.now()

}