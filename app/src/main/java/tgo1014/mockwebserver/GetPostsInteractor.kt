package tgo1014.mockwebserver

class GetPostsInteractor(private val api: Api) {
    fun execute() = api.getPosts().execute().body()!! // Never do this in production :)
}