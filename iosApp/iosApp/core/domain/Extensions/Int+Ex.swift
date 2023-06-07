
import Foundation

typealias Identifier = Int64
var RandomIdentifier: Identifier { Int64.random(in: .min ... .max) }
