//package com.bhavan
//
//import org.springframework.boot.CommandLineRunner
//import org.springframework.context.annotation.Bean
//
//
//@Component
//class DataInitializer {
//    @Bean
//    fun loadData(userRepository: UserRepository): CommandLineRunner {
//        return CommandLineRunner { args: Array<String?>? ->
//            // Insert some data
//            userRepository.save(User("Alice"))
//            userRepository.save(User("Bob"))
//            userRepository.save(User("Charlie"))
//            println("Sample users have been inserted.")
//        }
//    }
//}