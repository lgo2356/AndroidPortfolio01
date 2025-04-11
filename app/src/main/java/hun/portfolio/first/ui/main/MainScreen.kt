package hun.portfolio.first.ui.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.createGraph
import hun.portfolio.first.NavDestination
import hun.portfolio.first.ui.chat.ChannelAppBar

@Composable
fun MainScreen(
    uiState: MainUiState,
    chatListRoute: @Composable () -> Unit,
    onSelectedItem: (Int) -> Unit
) {
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { ChannelAppBar(title = uiState.topBarText) },
        bottomBar = { BottomNavigationBar(navController, onSelectedItem) }
    ) { paddingValue ->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValue)
        )

        val graph = navController.createGraph(NavDestination.MAIN_ROUTE) {
            composable(NavDestination.MAIN_ROUTE) {
                Box(modifier = Modifier.fillMaxSize()) {
                    Text(
                        text = "홈",
                        style = MaterialTheme.typography.labelLarge,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }

            composable(NavDestination.CHAT_LIST_ROUTE) {
                chatListRoute()
            }
        }

        NavHost(
            navController = navController,
            graph = graph,
            modifier = Modifier.padding(paddingValue)
        )
    }
}

@Composable
fun BottomNavigationBar(
    navController: NavController,
    onSelectedItem: (Int) -> Unit
) {
    val navItems = listOf(
        MainNavItem(
            title = "홈",
            icon = Icons.Default.Home,
            route = NavDestination.MAIN_ROUTE
        ),
        MainNavItem(
            title = "채팅 리스트",
            icon = Icons.Default.Person,
            route = NavDestination.CHAT_LIST_ROUTE
        )
    )

    val selectedNavIndex = rememberSaveable { mutableIntStateOf(0) }

    NavigationBar {
        navItems.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedNavIndex.intValue == index,
                onClick = {
                    selectedNavIndex.intValue = index

                    onSelectedItem(index)

                    navController.navigate(item.route)
                },
                icon = {
                    Icon(imageVector = item.icon, contentDescription = item.title)
                },
                label = { Text(item.title) }
            )
        }
    }
}

data class MainNavItem(
    val title: String,
    val icon: ImageVector,
    val route: String,
)

@Preview
@Composable
fun MainScreenPreview() {
    val uiState = MainUiState(topBarText = "홈")

    MainScreen(
        uiState = uiState,
        chatListRoute = {},
        onSelectedItem = {}
    )
}
