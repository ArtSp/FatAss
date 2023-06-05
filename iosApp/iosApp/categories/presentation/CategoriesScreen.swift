
import SwiftUI
import MPSwiftUI

struct CategoriesScreen: View {
    
    @ObservedObject var viewModel: ViewModel
    
    init(
        productsUseCase: ProductsUseCase
    ) {
        self.viewModel = ViewModel(productsUseCase: productsUseCase)
    }
    
    var body: some View {
        ZStack {
            if viewModel.state.isLoading.isEmpty {
                let value = viewModel.state.categories.isEmptyOrNil ? "False" : "True"
                Text("Have objects: \(viewModel.state.categories?.count ?? 0)")
            } else {
                ProgressView()
            }
        }.onChange(of: viewModel.state.error, perform: { error in
            print(error)
        })
        .onAppear {
            viewModel.startObserving()
            viewModel.onEvent(event: CategoriesEvent.LoadContent())
        }
        .onDisappear {
            viewModel.dispose()
        }
    }
}

extension CategoryItem: PlaceholderProvider {
    static public var placeholder: CategoryItem { CategoryItem(id: 0, name: "Name") }
}

// MARK: - Preview

struct CategoriesScreen_Previews: PreviewProvider {
    static var previews: some View {
        CategoriesScreen(productsUseCase: AppModule().productsUseCase)
    }
}
