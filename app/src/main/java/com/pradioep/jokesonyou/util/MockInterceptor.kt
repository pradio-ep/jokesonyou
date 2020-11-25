package com.pradioep.jokesonyou.util

import com.pradioep.jokesonyou.BuildConfig
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody

class MockInterceptor: Interceptor {

    private val SUCCESS_CODE = 200
    private val FAILED_CODE = 404
    private val BAD_GATEWAY_CODE = 502

    override fun intercept(chain: Interceptor.Chain): Response {
        if (BuildConfig.DEBUG) {
            val uri = chain.request().url.toUri().toString()
            return when {
                /**
                 * uncomment case below to add test case
                 * */
//                uri.contains("search") -> {
//                    mockResponse(chain, getResponse1)
//                }
//                uri.contains("categories") -> {
//                    mockFailedResponse(chain, getResponse2)
//                }
//                uri.contains("random") -> {
//                    mockResponse(chain, getResponse3)
//                }
                else -> {
                    chain.proceed(chain.request()).newBuilder().build()
                }
            }
        } else {
            return chain.proceed(chain.request()).newBuilder().build()
        }
    }

    private fun mockResponse(chain: Interceptor.Chain, response: String): Response {
        return chain.proceed(chain.request())
            .newBuilder()
            .code(SUCCESS_CODE)
            .protocol(Protocol.HTTP_2)
            .message(response)
            .body(response.toByteArray().toResponseBody("application/json".toMediaTypeOrNull()))
            .addHeader("content-type", "application/json")
            .build()
    }

    private fun mockFailedResponse(chain: Interceptor.Chain, response: String): Response {
        return chain.proceed(chain.request())
            .newBuilder()
            .code(FAILED_CODE)
            .protocol(Protocol.HTTP_2)
            .message(response)
            .body(response.toByteArray().toResponseBody("application/json".toMediaTypeOrNull()))
            .addHeader("content-type", "application/json")
            .build()
    }

    private val getResponse1 = "{\n" +
            "\"total\": 6,\n" +
            "\"result\": [\n" +
            "{\n" +
            "\"categories\": [],\n" +
            "\"created_at\": \"2020-01-05 13:42:20.568859\",\n" +
            "\"icon_url\": \"https://assets.chucknorris.host/img/avatar/chuck-norris.png\",\n" +
            "\"id\": \"N1SEvVeKQq-6uQbQ-MRR_Q\",\n" +
            "\"updated_at\": \"2020-01-05 13:42:20.568859\",\n" +
            "\"url\": \"https://api.chucknorris.io/jokes/N1SEvVeKQq-6uQbQ-MRR_Q\",\n" +
            "\"value\": \"If Chuck Norris uploaded a video on youtube no matter how stupid it is, Boom. Millions of views and subscribers. That's how Chuck Norris has more subscribers than pewdiepie, In Fact: Everyone in the whole world subscribed him.\"\n" +
            "},\n" +
            "{\n" +
            "\"categories\": [],\n" +
            "\"created_at\": \"2020-01-05 13:42:20.841843\",\n" +
            "\"icon_url\": \"https://assets.chucknorris.host/img/avatar/chuck-norris.png\",\n" +
            "\"id\": \"LLKDQrJGThuqSIqKtckx8g\",\n" +
            "\"updated_at\": \"2020-01-05 13:42:20.841843\",\n" +
            "\"url\": \"https://api.chucknorris.io/jokes/LLKDQrJGThuqSIqKtckx8g\",\n" +
            "\"value\": \"The Black Eyed Peas inspiration for \\\"Boom Boom Pow\\\" is about Chuck Norris famous lethal combination. 2 straight jabs creating the \\\"Boom\\\" sound and finishes you off with the roundhouse kick to the head creating the \\\"Pow\\\" sound.\"\n" +
            "},\n" +
            "{\n" +
            "\"categories\": [],\n" +
            "\"created_at\": \"2020-01-05 13:42:22.089095\",\n" +
            "\"icon_url\": \"https://assets.chucknorris.host/img/avatar/chuck-norris.png\",\n" +
            "\"id\": \"NYtE3UcYR5eKgQJO-SmwBw\",\n" +
            "\"updated_at\": \"2020-01-05 13:42:22.089095\",\n" +
            "\"url\": \"https://api.chucknorris.io/jokes/NYtE3UcYR5eKgQJO-SmwBw\",\n" +
            "\"value\": \"When Chuck Norris throws a boomerang, it comes back with a beer.\"\n" +
            "},\n" +
            "{\n" +
            "\"categories\": [],\n" +
            "\"created_at\": \"2020-01-05 13:42:23.240175\",\n" +
            "\"icon_url\": \"https://assets.chucknorris.host/img/avatar/chuck-norris.png\",\n" +
            "\"id\": \"sPWRZgw-QouuIJDA5CafVQ\",\n" +
            "\"updated_at\": \"2020-01-05 13:42:23.240175\",\n" +
            "\"url\": \"https://api.chucknorris.io/jokes/sPWRZgw-QouuIJDA5CafVQ\",\n" +
            "\"value\": \"Chuck Norris can time his attacks with pinpoint accuracy. For example, just as you start to relax in his presence - BOOM - his boot enters your face.\"\n" +
            "},\n" +
            "{\n" +
            "\"categories\": [],\n" +
            "\"created_at\": \"2020-01-05 13:42:24.696555\",\n" +
            "\"icon_url\": \"https://assets.chucknorris.host/img/avatar/chuck-norris.png\",\n" +
            "\"id\": \"U5O-DlUaSR6a7abWitzQ4w\",\n" +
            "\"updated_at\": \"2020-01-05 13:42:24.696555\",\n" +
            "\"url\": \"https://api.chucknorris.io/jokes/U5O-DlUaSR6a7abWitzQ4w\",\n" +
            "\"value\": \"Chuck Norris can de-forest 5 acres of the Amazon using a butterknife, a boomerang, and a pair of pliers.\"\n" +
            "},\n" +
            "{\n" +
            "\"categories\": [],\n" +
            "\"created_at\": \"2020-01-05 13:42:25.905626\",\n" +
            "\"icon_url\": \"https://assets.chucknorris.host/img/avatar/chuck-norris.png\",\n" +
            "\"id\": \"CviIHPRhTliBecAp0PhUUQ\",\n" +
            "\"updated_at\": \"2020-01-05 13:42:25.905626\",\n" +
            "\"url\": \"https://api.chucknorris.io/jokes/CviIHPRhTliBecAp0PhUUQ\",\n" +
            "\"value\": \"Only Chuck Norris can cause a Sonic Boom.\"\n" +
            "}\n" +
            "]\n" +
            "}"

    private val getResponse2 = "[\n" +
            "\"animal\",\n" +
            "\"career\",\n" +
            "\"celebrity\",\n" +
            "\"dev\",\n" +
            "\"explicit\",\n" +
            "\"fashion\",\n" +
            "\"food\",\n" +
            "\"history\",\n" +
            "\"money\",\n" +
            "\"movie\",\n" +
            "\"music\",\n" +
            "\"political\",\n" +
            "\"religion\",\n" +
            "\"science\",\n" +
            "\"sport\",\n" +
            "\"travel\"\n" +
            "]"

    private val getResponse3 = "{\n" +
            "\"categories\": [],\n" +
            "\"created_at\": \"2020-01-05 13:42:30.177068\",\n" +
            "\"icon_url\": \"https://assets.chucknorris.host/img/avatar/chuck-norris.png\",\n" +
            "\"id\": \"Z6OhAVjJT-W0NVnW5IZlug\",\n" +
            "\"updated_at\": \"2020-01-05 13:42:30.177068\",\n" +
            "\"url\": \"https://api.chucknorris.io/jokes/Z6OhAVjJT-W0NVnW5IZlug\",\n" +
            "\"value\": \"When Chuck Norris cuts onions, the onions cry!\"\n" +
            "}"
}