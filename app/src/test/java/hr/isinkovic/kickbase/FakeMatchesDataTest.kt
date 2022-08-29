package hr.isinkovic.kickbase

import hr.isinkovic.kickbase.api.Match
import hr.isinkovic.kickbase.api.Team

class FakeMatchesDataTest {

    val matches = listOf(
        Match(
            id = 6177,
            firstTeam = Team(
                name = "Bremen",
                id = 10,
                shortName = "BRE",
                goalsCount = 3,
                sp = 4149
            ),
            secondTeam = Team(
                name = "Frankfurt",
                id = 4,
                shortName = "SGE",
                goalsCount = 4,
                sp = 3074
            ),
            status = 1,
            matchDate = "2022-08-29T10:10:00Z"
        ),
        Match(
            id = 6178,
            firstTeam = Team(
                name = "Schalke",
                id = 8,
                shortName = "S04",
                goalsCount = 0,
                sp = 2402
            ),
            secondTeam = Team(
                name = "1.FC Union Berlin",
                id = 40,
                shortName = "FCU",
                goalsCount = 1,
                sp = 5747
            ),
            status = 1,
            matchDate = "2022-08-29T10:10:00Z"
        ),
        Match(
            id = 6179,
            firstTeam = Team(
                name = "Hertha",
                id = 20,
                shortName = "BSC",
                goalsCount = 0,
                sp = 2223
            ),
            secondTeam = Team(
                name = "Dortmund",
                id = 3,
                shortName = "BVB",
                goalsCount = 0,
                sp = 5236
            ),
            status = 1,
            matchDate = "2022-08-29T10:10:00Z"
        ),
        Match(
            id = 6180,
            firstTeam = Team(
                name = "Hoffenheim",
                id = 14,
                shortName = "TSG",
                goalsCount = 0,
                sp = 4936
            ),
            secondTeam = Team(
                name = "Augsburg",
                id = 13,
                shortName = "FCA",
                goalsCount = 0,
                sp = 2209
            ),
            status = 1,
            matchDate = "2022-08-29T10:10:00Z"
        )
    )
}