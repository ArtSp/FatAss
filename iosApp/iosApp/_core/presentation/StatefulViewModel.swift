
import Foundation

@dynamicMemberLookup
protocol StatefulViewModel: ObservableObject {
    associatedtype ViewState
    var state: ViewState { get set }
}

extension StatefulViewModel {
    subscript<Value>(
        dynamicMember keyPath: KeyPath<ViewState, Value>
    ) -> Value {
        state[keyPath: keyPath]
    }
}
