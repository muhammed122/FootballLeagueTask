package com.example.footballleaguetask.presentation.details

import BasicScreen
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.footballleaguetask.domain.entity.AreaModel
import com.example.footballleaguetask.domain.entity.CompetitionDetailsModel
import com.example.footballleaguetask.domain.entity.CompetitionModel
import com.example.footballleaguetask.domain.entity.SeasonModel
import com.example.footballleaguetask.domain.entity.WinnerModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CompetitionDetailsScreen(
    id: Int,
    viewModel: CompetitionDetailsViewModel = hiltViewModel()
) {
    SideEffect {
        viewModel.handleAction(CompetitionDetailsAction.LoadCompetition(id))
    }
    BasicScreen(
        viewModel = viewModel,
        onErrorDismissed = { /* optional */ },
        toolbar = {
            TopAppBar(title = { Text("Competition Details") })
        },
        content = { state ->
            state.competition?.let { competition ->
                CompetitionDetailsContent(competition)
            } ?: Text(
                "Loading...",
                modifier = Modifier
                    .fillMaxSize()

            )
        }
    )
}


@Composable
fun CompetitionDetailsContent(competition: CompetitionDetailsModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        AsyncImage(
            model = competition.emblem,
            contentDescription = competition.name,
            placeholder = painterResource(id = android.R.drawable.ic_menu_report_image),
            error = painterResource(id = android.R.drawable.ic_menu_report_image),
            modifier = Modifier
                .size(100.dp)
                .align(Alignment.CenterHorizontally)
                .clip(CircleShape)
        )

        Text(
            text = competition.name,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        InfoRow("Code", competition.code)
        InfoRow("Type", competition.type)
        InfoRow("Area", competition.area.name)
        InfoRow("Start Date", competition.currentSeason?.startDate ?: "--")
        InfoRow("End Date", competition.currentSeason?.endDate ?: "--")
        InfoRow("Current Matchday", competition.currentSeason?.currentMatchday?.toString() ?: "--")

        competition.currentSeason?.winner?.let { winner ->
            Spacer(Modifier.height(8.dp))
            Text("🏆 Last Winner", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)

            AsyncImage(
                model = winner.crest,
                contentDescription = winner.name,
                placeholder = painterResource(id = android.R.drawable.ic_menu_report_image),
                error = painterResource(id = android.R.drawable.ic_menu_report_image),
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
            )
            InfoRow("Name", winner.name)
            InfoRow("Short Name", winner.shortName)
            InfoRow("TLA", winner.tla)
            InfoRow("Founded", winner.founded.toString())
            InfoRow("Venue", winner.venue)
            InfoRow("Website", winner.website)
        }
    }
}

@Composable
fun InfoRow(label: String, value: String) {
    Row {
        Text("$label: ", fontWeight = FontWeight.SemiBold)
        Text(value)
    }
}

@Preview(showBackground = true)
@Composable
fun CompetitionDetailsContentPreview() {
    val mockCompetition = CompetitionDetailsModel(
        id = 1,
        name = "UEFA Champions League",
        code = "CL",
        type = "CUP",
        emblem = "https://crests.football-data.org/CL.png",
        area = AreaModel(
            id = 2077,
            name = "Europe",
            code = "EUR",
        ),
        currentSeason = SeasonModel(
            id = 2350,
            startDate = "2024-09-17",
            endDate = "2025-05-31",
            currentMatchday = 2,
            winner = WinnerModel(
                id = 86,
                name = "Real Madrid CF",
                shortName = "Real Madrid",
                tla = "RMA",
                crest = "https://crests.football-data.org/86.png",
                address = "Avenida Concha Espina, 1 Madrid 28036",
                website = "http://www.realmadrid.com",
                founded = 1902,
                clubColors = "White / Purple",
                venue = "Estadio Santiago Bernabéu"
            )
        )
    )

    MaterialTheme {
        CompetitionDetailsContent(competition = mockCompetition)
    }
}
