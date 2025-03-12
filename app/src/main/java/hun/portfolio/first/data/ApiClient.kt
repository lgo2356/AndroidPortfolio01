package hun.portfolio.first.data

import com.google.gson.GsonBuilder
import hun.portfolio.first.BuildConfig
import hun.portfolio.first.data.message.MessageRequest
import hun.portfolio.first.data.message.MessageResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("sendMessage")
    suspend fun sendMessage(@Body message: MessageRequest): Response<MessageResponse>
}

object ApiClient {
    private var INSTANCE: ApiService? = null
    private val gson = GsonBuilder().setLenient().create()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.API_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    fun getInstance(): ApiService {
        return INSTANCE ?: synchronized(this) {
            val service = retrofit.create(ApiService::class.java)

            INSTANCE = service
            service
        }
    }
}
