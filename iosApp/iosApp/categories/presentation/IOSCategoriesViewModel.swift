
import Foundation

extension CategoriesScreen {
    class IOSCategoriesViewModel: StatefulViewModel {
        
        private var handle: DisposableHandle?
        private let viewModel: CategoriesViewModel
        
        @Published var state: CategoriesState = CategoriesState(
            isChoosingLanguage: false,
            error: nil,
            categories: nil,
            selectedCategory: nil,
            isLoading: .init()
        )
        
        init(
            productsUseCase: ProductsUseCase
        ) {
            viewModel = CategoriesViewModel(productsUseCase: productsUseCase, coroutineScope: nil)
        }
        
        func onEvent(
            event: CategoriesEvent
        ) {
            self.viewModel.onEvent(event: event)
        }
        
        func startObserving() {
            handle = viewModel.state.subscribe(onCollect: { [weak self] state in
                if let state = state {
                    self?.state = state
                }
            })
        }
        
        func dispose() {
            handle?.dispose()
        }
        
    }    
}
