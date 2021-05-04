package id.expert.capstoneproject.core.di

import androidx.room.Room
import id.expert.capstoneproject.core.BuildConfig.*
import id.expert.capstoneproject.core.data.MoviesRepository
import id.expert.capstoneproject.core.data.source.local.LocalDataSource
import id.expert.capstoneproject.core.data.source.local.room.MoviesDatabase
import id.expert.capstoneproject.core.data.source.remote.RemoteDataSource
import id.expert.capstoneproject.core.data.source.remote.network.ApiService
import id.expert.capstoneproject.core.domain.repository.IMoviesRepository
import id.expert.capstoneproject.core.utils.AppExecutors
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val databaseModule = module {
    factory { get<MoviesDatabase>().moviesDao() }
    single {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("moviesapp".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            MoviesDatabase::class.java, "Movies.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}

val networkModule = module {
    single {
        val logging = HttpLoggingInterceptor()
        if (DEBUG) {
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        }
        OkHttpClient.Builder()
            .addInterceptor(logging)
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(
                CertificatePinner.Builder()
                    .add(CERT_URL, CERT_PINNING0)
                    .add(CERT_URL, CERT_PINNING1)
                    .build()
            )
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IMoviesRepository> {
        MoviesRepository(
            get(),
            get(),
            get()
        )
    }
}