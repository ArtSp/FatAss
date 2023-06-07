
import SwiftUI
import MPSwiftUI

struct ProductsScreen: View {
    
    @ObservedObject var viewModel: ViewModel
    @Environment(\.appModule) private var appModule
    private let category: CategoryItem
    
    init(
        category: CategoryItem,
        productsUseCase: ProductsUseCase
    ) {
       viewModel = ViewModel(productsUseCase: productsUseCase)
       self.category = category
    }
    
    func productView(
        _ product: ProductItem
    ) -> some View {
        ZStack(alignment: .topLeading) {
            
            if let url = URL(string: product.thumbnail) {
                GeometryReader { geo in
                    MPAsyncImage(url: url) { image in
                        image
                            .resizable()
                            .aspectRatio(contentMode: .fill)
                            .frame(width: geo.size.width, alignment: .center)
                    }
                }
            }
            
            Text(product.title).textStyle(.body, color: .textBlack)
                .padding(5)
                .background(.thinMaterial)
                .cornerRadius(6)
                .padding()
        }
            .frame(maxWidth: .infinity)
            .aspectRatio(1, contentMode: .fill)
            .background(Color.lightBlueGrey)
            .cornerRadius(8)
            .shadow(radius: 1)
    }
    
    func productsGrid(
        _ products: [ProductItem]
    ) -> some View {
        Grid {
            let range = Array(stride(from: 0, to: products.count, by: 2))
            ForEach(range, id: \.self) { i in
                GridRow {
                    Unwrap(products[safe: i]) { productView($0) }
                    Unwrap(products[safe: i + 1]) { productView($0) }
                }
            }
        }
        .padding()
        .scrollViewIfNeeded(.vertical)
    }
    
    var body: some View {
        ZStack {
            Unwrap(viewModel.products) { products in
                productsGrid(products)
            } fallbackContent: {
                if viewModel.isLoading.isEmpty {
                    Text("products.noData")
                        .textStyle(.title, color: .gray)
                } else {
                    let placeholders = ProductItem.placeholders(count: 6)
                    productsGrid(placeholders)
                        .shimmedAndRedacted()
                }
            }
        }
            .navigationTitle(category.displayName)
            .alert(isPresented: Binding(
                get: { !viewModel.error.isNil },
                set: { _ in viewModel.onEvent(event: .ClearError()) })
            ) {
                Alert(title: Text(viewModel.error?.message ?? ""))
            }
            .onAppear {
                viewModel.startObserving()
                viewModel.onEvent(event: .LoadContent(category: category))
            }
            .onDisappear {
                viewModel.dispose()
            }
    }
}

extension ProductItem: Identifiable, PlaceholderProvider {
    static public var placeholder: ProductItem {
        ProductItem(
            id: RandomIdentifier,
            title: "Title",
            description: "Destination",
            thumbnail: "https://storage.googleapis.com/proudcity/mebanenc/uploads/2021/03/placeholder-image.png",
            price: 10,
            discountPercentage: 0) }
}

// MARK: - Preview

struct ProductsScreen_Previews: PreviewProvider {
    static var previews: some View {
        ProductsScreen(
            category: .placeholder,
            productsUseCase: AppModuleFake().productsUseCase
        )
            .previewInNavigationView()
    }
}
