package org.test.tutorial_client

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import org.test.tutorial_client.post.data.Post
import org.test.tutorial_client.post.data.PostApi

@Composable
fun App() {
    val client = createHttpClient()
    val postApi = remember { PostApi(client) }
    val posts = remember { mutableStateListOf<Post>() }
    val scope = rememberCoroutineScope()

    // 앱이 시작될 때 로그 출력 (안정적인 위치)
    LaunchedEffect(Unit) {
        Logger.info("앱 시작됨")
    }

    MaterialTheme {
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp)
        ) {
            Button(
                onClick = {
                    scope.launch {
                        try {
                            val fetchedPosts = postApi.getAllPosts()
                            posts.clear()
                            posts.addAll(fetchedPosts)
                            Logger.info("게시글 ${fetchedPosts.size}개 가져옴")
                        } catch (e: Exception) {
                            Logger.error("게시글 불러오기 실패: ${e.message}")
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Fetch Posts")
            }
            Spacer(modifier = Modifier.height(16.dp)) // 버튼과 리스트 간격 추가
            LazyColumn {
                items(posts) { post ->
                    PostCard(post)
                }
            }
        }
    }
}

@Composable
fun PostCard(post: Post) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        elevation = 4.dp
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = post.title,
                style = MaterialTheme.typography.h6
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = post.body,
                style = MaterialTheme.typography.body2
            )
        }
    }
}

@Composable
fun GreetingView(text: String) {
    Text(
        text = text,
        modifier = Modifier.padding(8.dp),
        style = MaterialTheme.typography.h6
    )
}
