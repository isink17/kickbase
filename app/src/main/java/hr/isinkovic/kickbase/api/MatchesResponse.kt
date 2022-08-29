package hr.isinkovic.kickbase.api

import com.google.gson.annotations.SerializedName

data class MatchesResponse(
    @SerializedName("sn") val season: String,
    @SerializedName("day") val matchDay: Int,
    @SerializedName("nd") val matchDaysInSeason: Int,
    @SerializedName("e") val matches: List<Match>
)

data class Match(
    @SerializedName("i") val id: Int,
    @SerializedName("t1") val firstTeam: Team,
    @SerializedName("t2") val secondTeam: Team,
    @SerializedName("s") val status: Int,
    @SerializedName("d") val matchDate: String
)

data class Team(
    @SerializedName("n") val name: String,
    @SerializedName("i") val id: Int,
    @SerializedName("s") val shortName: String,
    @SerializedName("g") val goalsCount: Int,
    val sp: Int
)