//
//  ProductsScreen.swift
//  iosApp
//
//  Created by Artjoms Spole on 06/06/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct ProductsScreen: View {
    
    //    @ObservedObject var viewModel: ViewModel
    @Environment(\.appModule) private var appModule
    
    init(
        category: CategoryItem,
        productsUseCase: ProductsUseCase
    ) {
//        viewModel = ViewModel(productsUseCase: productsUseCase)
    }
    
    var body: some View {
        Text(/*@START_MENU_TOKEN@*/"Hello, World!"/*@END_MENU_TOKEN@*/)
    }
}

struct ProductsScreen_Previews: PreviewProvider {
    static var previews: some View {
        ProductsScreen(category: .placeholder, productsUseCase: AppModuleFake().productsUseCase)
            .previewInNavigationView()
    }
}
