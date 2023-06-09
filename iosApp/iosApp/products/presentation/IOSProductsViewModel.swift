
import Foundation

extension ProductsScreen {
   class IOSProductsViewModel: StatefulViewModel {

       private var handle: DisposableHandle?
       private let viewModel: ProductsViewModel

       @Published var state: ProductsState = ProductsState(
           products: nil,
           error: nil,
           isLoading: .init()
       )

       init(
            productsUseCase: ProductsUseCase
       ) {
           viewModel = ProductsViewModel(productsUseCase: productsUseCase, coroutineScope: nil)
       }

       func onEvent(
            event: ProductsEvent
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
