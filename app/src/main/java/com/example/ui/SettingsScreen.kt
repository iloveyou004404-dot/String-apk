package com.example.ui

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.R
import com.example.model.SettingItem
import com.example.ui.components.*
import com.example.ui.theme.*
import kotlinx.coroutines.launch

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    val showScrollToTop by remember {
        derivedStateOf { listState.firstVisibleItemIndex > 2 }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(LiquidBackgroundDark)
    ) {
        // High-res Liquid Mesh Wallpaper background
        Image(
            painter = painterResource(id = R.drawable.bg_liquid_mesh_1790873156748),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .alpha(0.42f)
        )

        // Dark gradient scrim overlay to elevate liquid cards and maintain contrast
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color(0xCC060913),
                            Color(0x99060913),
                            Color(0xF0060913)
                        )
                    )
                )
        )

        // Main Screen Content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
        ) {
            // Header: Status Bar + Title + Search
            LiquidSystemHeader(
                searchQuery = uiState.searchQuery,
                onSearchQueryChange = viewModel::onSearchQueryChanged,
                totalCount = uiState.allSettings.size,
                matchedCount = uiState.filteredSettings.size,
                accentTheme = uiState.accentTheme,
                onProfileClick = viewModel::onOpenProfile,
                onFilterClick = {
                    // Quick clear or toggle filter
                    if (uiState.selectedCategoryFilter != null || uiState.selectedLetter != null) {
                        viewModel.onLetterSelected(null)
                        viewModel.onCategoryFilterSelected(null)
                    }
                }
            )

            // Primary Lazy List of Setting Categories & Cards
            LazyColumn(
                state = listState,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                contentPadding = PaddingValues(bottom = 96.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Item 0: Quick Glance Cards (only shown if not actively filtering by query)
                if (uiState.searchQuery.isEmpty() && uiState.selectedLetter == null) {
                    item(key = "quick_glances") {
                        Column {
                            LiquidQuickGlanceRow(
                                onCardClick = viewModel::onGlanceCardClicked,
                                modifier = Modifier.padding(bottom = 12.dp)
                            )

                            // Category Quick Filter Pills
                            CategoryFilterPillRow(
                                selectedCategory = uiState.selectedCategoryFilter,
                                onSelectCategory = viewModel::onCategoryFilterSelected,
                                accentTheme = uiState.accentTheme
                            )
                        }
                    }
                }

                // Item 1: A-Z Alphabet Scrubber Bar
                item(key = "alphabet_scrubber") {
                    LiquidAlphabetBar(
                        selectedLetter = uiState.selectedLetter,
                        onLetterSelected = viewModel::onLetterSelected,
                        availableLetters = uiState.availableLetters,
                        accentTheme = uiState.accentTheme
                    )
                }

                // Grouped Settings A through Z
                if (uiState.groupedByLetter.isEmpty()) {
                    item(key = "empty_state") {
                        EmptySearchResultsCard(
                            query = uiState.searchQuery,
                            onReset = {
                                viewModel.onSearchQueryChanged("")
                                viewModel.onLetterSelected(null)
                                viewModel.onCategoryFilterSelected(null)
                            },
                            accentTheme = uiState.accentTheme
                        )
                    }
                } else {
                    uiState.groupedByLetter.forEach { (letter, itemsInGroup) ->
                        item(key = "group_$letter") {
                            LiquidAlphabetGroupCard(
                                letter = letter,
                                items = itemsInGroup,
                                toggleStates = uiState.toggleStates,
                                onToggle = viewModel::onToggleSetting,
                                onItemClick = viewModel::onOpenDetail,
                                accentTheme = uiState.accentTheme,
                                glassOpacity = uiState.glassOpacity,
                                enableShimmer = uiState.enableShimmer
                            )
                        }
                    }
                }
            }
        }

        // Floating Action Button: Scroll back to top
        AnimatedVisibility(
            visible = showScrollToTop,
            enter = fadeIn(animationSpec = tween(200)) + scaleIn(),
            exit = fadeOut(animationSpec = tween(200)) + scaleOut(),
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .navigationBarsPadding()
                .padding(24.dp)
        ) {
            LiquidGlassSurface(
                modifier = Modifier
                    .size(50.dp)
                    .testTag("scroll_to_top_button"),
                shape = CircleShape,
                backgroundColor = Color(0x35FFFFFF),
                borderHighlightColor = uiState.accentTheme.primary.copy(alpha = 0.8f),
                ambientGlowColor = uiState.accentTheme.primary.copy(alpha = 0.3f),
                elevation = 8.dp,
                onClick = {
                    coroutineScope.launch {
                        listState.animateScrollToItem(0)
                    }
                }
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowUpward,
                        contentDescription = "Scroll to top",
                        tint = uiState.accentTheme.primary,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }

        // Setting Detail Inspector Bottom Sheet
        uiState.selectedItemForDetail?.let { item ->
            val isEnabled = uiState.toggleStates[item.id] ?: item.defaultEnabled
            LiquidSettingDetailSheet(
                item = item,
                isEnabled = isEnabled,
                onToggle = { enabled -> viewModel.onToggleSetting(item.id, enabled) },
                accentTheme = uiState.accentTheme,
                onDismiss = viewModel::onDismissDetail
            )
        }

        // Device Profile & Theme Sheet
        if (uiState.showProfileSheet) {
            LiquidProfileSheet(
                currentTheme = uiState.accentTheme,
                onSelectTheme = viewModel::onSelectTheme,
                enableShimmer = uiState.enableShimmer,
                onToggleShimmer = viewModel::onToggleShimmer,
                glassOpacity = uiState.glassOpacity,
                onGlassOpacityChange = viewModel::onGlassOpacityChanged,
                onDismiss = viewModel::onDismissProfile
            )
        }

        // QR Scanner Viewfinder Sheet
        if (uiState.showQRScanner) {
            LiquidQRScannerSheet(
                accentTheme = uiState.accentTheme,
                onDismiss = viewModel::onDismissQRScanner
            )
        }
    }
}

@Composable
private fun LiquidAlphabetGroupCard(
    letter: Char,
    items: List<SettingItem>,
    toggleStates: Map<String, Boolean>,
    onToggle: (String, Boolean) -> Unit,
    onItemClick: (SettingItem) -> Unit,
    accentTheme: GlassAccentTheme,
    glassOpacity: Float,
    enableShimmer: Boolean,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        // Group Header Pill
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Illuminated Glass Letter Pill
                Box(
                    modifier = Modifier
                        .size(30.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(accentTheme.primary.copy(alpha = 0.22f)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = letter.toString(),
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = accentTheme.primary
                        )
                    )
                }

                Text(
                    text = "Category $letter",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = LiquidTextPrimary,
                        fontSize = 15.sp
                    )
                )
            }

            Text(
                text = "${items.size} ${if (items.size == 1) "item" else "items"}",
                style = MaterialTheme.typography.labelSmall.copy(
                    color = LiquidTextSecondary,
                    fontSize = 11.sp
                )
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        // Main Frosted Glass Card enclosing all items for this letter
        LiquidGlassSurface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(22.dp),
            backgroundColor = Color(0xFFFFFFFF).copy(alpha = glassOpacity),
            borderHighlightColor = Color(0x38FFFFFF),
            borderShadowColor = Color(0x08FFFFFF),
            elevation = 6.dp,
            enableShimmer = enableShimmer
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                items.forEachIndexed { index, item ->
                    val isEnabled = toggleStates[item.id] ?: item.defaultEnabled
                    val isLast = index == items.lastIndex

                    LiquidSettingItemRow(
                        item = item,
                        isEnabled = isEnabled,
                        onToggle = { enabled -> onToggle(item.id, enabled) },
                        onClick = { onItemClick(item) },
                        showDivider = !isLast
                    )
                }
            }
        }
    }
}

@Composable
private fun CategoryFilterPillRow(
    selectedCategory: String?,
    onSelectCategory: (String?) -> Unit,
    accentTheme: GlassAccentTheme,
    modifier: Modifier = Modifier
) {
    val categories = listOf("Network", "Security", "Display", "Audio", "Hardware", "Accessibility")

    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 4.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(categories) { category ->
            val isSelected = selectedCategory == category
            FilterChip(
                selected = isSelected,
                onClick = { onSelectCategory(if (isSelected) null else category) },
                label = {
                    Text(
                        text = category,
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                        )
                    )
                },
                leadingIcon = if (isSelected) {
                    {
                        Icon(
                            imageVector = Icons.Default.Done,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                } else null,
                colors = FilterChipDefaults.filterChipColors(
                    containerColor = Color(0x14FFFFFF),
                    labelColor = LiquidTextSecondary,
                    selectedContainerColor = accentTheme.primary.copy(alpha = 0.22f),
                    selectedLabelColor = accentTheme.primary,
                    selectedLeadingIconColor = accentTheme.primary
                ),
                border = FilterChipDefaults.filterChipBorder(
                    enabled = true,
                    selected = isSelected,
                    borderColor = Color(0x20FFFFFF),
                    selectedBorderColor = accentTheme.primary.copy(alpha = 0.5f)
                ),
                shape = RoundedCornerShape(12.dp)
            )
        }
    }
}

@Composable
private fun EmptySearchResultsCard(
    query: String,
    onReset: () -> Unit,
    accentTheme: GlassAccentTheme
) {
    LiquidGlassSurface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        shape = RoundedCornerShape(24.dp),
        backgroundColor = Color(0x18FFFFFF)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "No matching settings",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = LiquidTextPrimary
                )
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "No results found for \"$query\". Check spelling or clear filters.",
                style = MaterialTheme.typography.bodySmall.copy(
                    color = LiquidTextSecondary
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = onReset,
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(containerColor = accentTheme.primary)
            ) {
                Text(
                    text = "Clear Filter",
                    color = Color(0xFF060913),
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
