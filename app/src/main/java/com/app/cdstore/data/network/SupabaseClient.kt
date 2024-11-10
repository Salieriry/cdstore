package com.app.cdstore.data.network

import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest


object SupabaseClient {
    val client = createSupabaseClient(
        supabaseUrl = "https://rctdgkacsjzxjtvlmtgm.supabase.co",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InJjdGRna2Fjc2p6eGp0dmxtdGdtIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MzA2ODY5ODAsImV4cCI6MjA0NjI2Mjk4MH0.YuRau0wzIsTq2IkWMnsSjpUDanvwQVK3q7hkVuIIyGo"
    ) {
        install(Auth)
        install(Postgrest)
    }
}