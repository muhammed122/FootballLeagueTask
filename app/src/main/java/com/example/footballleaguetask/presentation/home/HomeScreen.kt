package com.example.footballleaguetask.presentation.home

import BasicScreen
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.footballleaguetask.domain.entity.AreaModel
import com.example.footballleaguetask.domain.entity.CompetitionModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onNavigateToDetails: (Int) -> Unit,
    ) {
    val snackbarHostState = remember { SnackbarHostState() }
    var latestEvent by remember { mutableStateOf<HomeViewEvent?>(null) }

    BasicScreen(
        viewModel = viewModel,
        onErrorDismissed = { viewModel.clearError() },
        snackbarHostState = snackbarHostState,
        toolbar = {
            TopAppBar(
                title = { Text("Areas & Competitions") }
            )
        },
        content = { state ->
            AreaList(
                state = state,
                onToggleExpand = { areaId ->
                    viewModel.toggleExpandedArea(areaId)
                    if (!state.expandedAreaIds.contains(areaId)) {
                        viewModel.handleAction(HomeViewAction.LoadCompetitions(areaId))
                    }
                },
                onCompetitionClick = { competitionId ->
                    viewModel.handleAction(HomeViewAction.NavigateToCompetitonDetails(competitionId))
                }
            )
        },
         onEvent = { event ->
            latestEvent = event
        }
    )

    LaunchedEffect(latestEvent) {
        when (val event = latestEvent) {
            is HomeViewEvent.NavigateToCompetitionDetails -> {
                onNavigateToDetails(event.id)
            }

            is HomeViewEvent.NoInternetConnection -> {
                snackbarHostState.showSnackbar("No internet connection")
            }

            else -> Unit
        }
    }
}


@Composable
fun AreaList(
    state: HomeViewState,
    onToggleExpand: (Int) -> Unit,
    onCompetitionClick: (Int) -> Unit,
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(items = state.areas, key = { it.id }) { area ->
            val areaCompetitions = state.competitions[area.id] ?: emptyList()
            ExpandableAreaItem(
                area = area,
                isExpanded = area.id in state.expandedAreaIds,
                competitions = areaCompetitions,
                onToggle = { onToggleExpand(area.id) },
                onCompetitionClick = { competitionId -> onCompetitionClick.invoke(competitionId) }
            )
        }
    }
}


@Composable
fun ExpandableAreaItem(
    area: AreaModel,
    isExpanded: Boolean,
    competitions: List<CompetitionModel>,
    onToggle: () -> Unit,
    onCompetitionClick: (Int) -> Unit,
) {
    val rotation by animateFloatAsState(
        targetValue = if (isExpanded) 180f else 0f,
        label = "arrowRotation"
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White)
            .clickable { onToggle() }
            .padding(16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "${area.name} (${area.code})",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f)
            )

            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = if (isExpanded) "Collapse" else "Expand",
                modifier = Modifier.rotate(rotation)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        AnimatedVisibility(visible = isExpanded) {
            Column {
                if (competitions.isEmpty()) {
                    Text("No competitions", modifier = Modifier.padding(8.dp))
                } else {
                    competitions.forEach {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp)
                                .clickable {
                                    onCompetitionClick.invoke(it.id)
                                }
                        ) {
                            AsyncImage(
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data(it.flag)
                                    .crossfade(true)
                                    .build(),
                                contentDescription = null,
                                placeholder = painterResource(id = android.R.drawable.ic_menu_report_image),
                                error = painterResource(id = android.R.drawable.ic_menu_report_image),
                                modifier = Modifier
                                    .size(24.dp)
                                    .clip(CircleShape)
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Column {
                                Text(text = it.name, fontWeight = FontWeight.Medium)
                                Text(
                                    text = it.type,
                                    style = MaterialTheme.typography.labelSmall
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

