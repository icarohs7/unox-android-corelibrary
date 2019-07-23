package base.corelibrary.domain.adapter

import androidx.room.TypeConverter
import java.util.Date

/**
 * Room type converter for
 * time based types
 */
class RoomTimeTypeConverters {
    @TypeConverter
    fun fromDate(data: Date): Long {
        return data.time
    }

    @TypeConverter
    fun toDate(data: Long): Date {
        return Date(data)
    }
}