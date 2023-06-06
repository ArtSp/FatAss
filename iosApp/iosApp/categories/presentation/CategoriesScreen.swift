
import SwiftUI
import MPSwiftUI

struct CategoriesScreen: View {
    
    @ObservedObject var viewModel: ViewModel
    @Environment(\.appModule) private var appModule
    @State private var showsCatgory: CategoryItem?
    
    init(
        productsUseCase: ProductsUseCase
    ) {
        viewModel = ViewModel(productsUseCase: productsUseCase)
    }

    func categoriesStack(
        categories: [CategoryItem]
    ) -> some View {
        VStack(alignment: .leading) {
            ForEach(categories) { it in
                Button(action: { showsCatgory = it }) {
                    HStack {
                        Text(it.name)
                        Spacer()
                        Image(systemName: "chevron.forward")
                    }
                    .foregroundColor(.accentViolet)
                    .frame(maxWidth: .infinity, alignment: .leading)
                    .padding()
                    .background(Color.lightBlueGrey)
                    .cornerRadius(8)
                }
            }
            
            Spacer()
        }
        .textStyle(.body)
        .padding()
        .scrollViewIfNeeded(.vertical)
    }
    
    var body: some View {
        ZStack {
            Unwrap(viewModel.categories) { categories in
                categoriesStack(categories: categories)
            } fallbackContent: {
                categoriesStack(categories: CategoryItem.placeholders(count: 5))
                    .shimmedAndRedacted()
                    .isHidden(viewModel.isLoading.isEmpty)
            }
        }
        .navigationTitle("categories.navigation.title")
        .alert(isPresented: Binding(
            get: { !viewModel.error.isNil },
            set: { _ in viewModel.onEvent(event: CategoriesEvent.ClearError()) })
        ) {
            Alert(title: Text(viewModel.error?.message ?? ""))
        }
        .navigation(item: $showsCatgory) { category in
            ProductsScreen(category: category, productsUseCase: appModule.productsUseCase)
        }
        .onAppear {
            viewModel.startObserving()
            viewModel.onEvent(event: CategoriesEvent.LoadContent())
        }
        .onDisappear {
            viewModel.dispose()
        }
    }
}

extension CategoryItem: Identifiable, PlaceholderProvider {
    static public var placeholder: CategoryItem { CategoryItem(id: RandomIdentifier, name: "Name") }
}

// MARK: - Preview

struct CategoriesScreen_Previews: PreviewProvider {
    
    static var previews: some View {
        CategoriesScreen(productsUseCase: AppModuleFake().productsUseCase)
            .previewInNavigationView()
    }
}
