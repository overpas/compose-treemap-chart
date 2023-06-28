//
//  IosApp.swift
//  ios
//
//  Created by Shurmilov, Pavel on 6/25/23.
//

import SwiftUI

@main
struct IosApp: App {
    var body: some Scene {
        WindowGroup {
            ZStack {
                Color.white.ignoresSafeArea(.all) // status bar color
                ContentView()
            }.preferredColorScheme(.light)
        }
    }
}
