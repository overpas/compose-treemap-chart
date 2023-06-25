//
//  ContentView.swift
//  ios
//
//  Created by Shurmilov, Pavel on 6/25/23.
//

import SwiftUI
import UIKit
import Shared

struct ContentView: View {
    var body: some View {
        ComposeView()
            .ignoresSafeArea(.all, edges: .bottom) // Compose has own keyboard handler
    }
}

struct ComposeView: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> UIViewController {
        Main_iosKt.MainViewController()
    }
    
    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
