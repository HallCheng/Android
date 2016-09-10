//
// Created by Administrator on 2016/5/16.
//

#ifndef HOOK_ARTCONSTS_H_H
#define HOOK_ARTCONSTS_H_H

#include <stdint.h>

static constexpr uint32_t kAccPublic =       0x0001;  // class, field, method, ic
static constexpr uint32_t kAccPrivate =      0x0002;  // field, method, ic
static constexpr uint32_t kAccProtected =    0x0004;  // field, method, ic
static constexpr uint32_t kAccStatic =       0x0008;  // field, method, ic
static constexpr uint32_t kAccFinal =        0x0010;  // class, field, method, ic
static constexpr uint32_t kAccSynchronized = 0x0020;  // method (only allowed on natives)
static constexpr uint32_t kAccSuper =        0x0020;  // class (not used in dex)
static constexpr uint32_t kAccVolatile =     0x0040;  // field
static constexpr uint32_t kAccBridge =       0x0040;  // method (1.5)
static constexpr uint32_t kAccTransient =    0x0080;  // field
static constexpr uint32_t kAccVarargs =      0x0080;  // method (1.5)
static constexpr uint32_t kAccNative =       0x0100;  // method
static constexpr uint32_t kAccInterface =    0x0200;  // class, ic
static constexpr uint32_t kAccAbstract =     0x0400;  // class, method, ic
static constexpr uint32_t kAccStrict =       0x0800;  // method
static constexpr uint32_t kAccSynthetic =    0x1000;  // class, field, method, ic
static constexpr uint32_t kAccAnnotation =   0x2000;  // class, ic (1.5)
static constexpr uint32_t kAccEnum =         0x4000;  // class, field, ic (1.5)

static constexpr uint32_t kAccJavaFlagsMask = 0xffff;  // bits set from Java sources (low 16)

static constexpr uint32_t kAccConstructor =          0x00010000;  // method (dex only) <(cl)init>
static constexpr uint32_t kAccDeclaredSynchronized = 0x00020000;  // method (dex only)
static constexpr uint32_t kAccClassIsProxy =         0x00040000;  // class  (dex only)
static constexpr uint32_t kAccPreverified =          0x00080000;  // class (runtime),
// method (dex only)
static constexpr uint32_t kAccFastNative =           0x00080000;  // method (dex only)
static constexpr uint32_t kAccMiranda =              0x00200000;  // method (dex only)

// Flag is set if the compiler decides it is not worth trying
// to inline the method. This avoids other callers to try it again and again.
static constexpr uint32_t kAccDontInline =           0x00400000;  // method (dex only)

// Special runtime-only flags.
// Note: if only kAccClassIsReference is set, we have a soft reference.

// class/ancestor overrides finalize()
static constexpr uint32_t kAccClassIsFinalizable        = 0x80000000;
// class is a soft/weak/phantom ref
static constexpr uint32_t kAccClassIsReference          = 0x08000000;
// class is a weak reference
static constexpr uint32_t kAccClassIsWeakReference      = 0x04000000;
// class is a finalizer reference
static constexpr uint32_t kAccClassIsFinalizerReference = 0x02000000;
// class is a phantom reference
static constexpr uint32_t kAccClassIsPhantomReference   = 0x01000000;
// class is the string class
static constexpr uint32_t kAccClassIsStringClass        = 0x00800000;

static constexpr uint32_t kAccReferenceFlagsMask = (kAccClassIsReference
                                                    | kAccClassIsWeakReference
                                                    | kAccClassIsFinalizerReference
                                                    | kAccClassIsPhantomReference);

// Valid (meaningful) bits for a field.
static constexpr uint32_t kAccValidFieldFlags = kAccPublic | kAccPrivate | kAccProtected |
                                                kAccStatic | kAccFinal | kAccVolatile | kAccTransient | kAccSynthetic | kAccEnum;

// Valid (meaningful) bits for a method.
static constexpr uint32_t kAccValidMethodFlags = kAccPublic | kAccPrivate | kAccProtected |
                                                 kAccStatic | kAccFinal | kAccSynchronized | kAccBridge | kAccVarargs | kAccNative |
                                                 kAccAbstract | kAccStrict | kAccSynthetic | kAccMiranda | kAccConstructor |
                                                 kAccDeclaredSynchronized;

// Valid (meaningful) bits for a class (not interface).
// Note 1. These are positive bits. Other bits may have to be zero.
// Note 2. Inner classes can expose more access flags to Java programs. That is handled by libcore.
static constexpr uint32_t kAccValidClassFlags = kAccPublic | kAccFinal | kAccSuper |
                                                kAccAbstract | kAccSynthetic | kAccEnum;

// Valid (meaningful) bits for an interface.
// Note 1. Annotations are interfaces.
// Note 2. These are positive bits. Other bits may have to be zero.
// Note 3. Inner classes can expose more access flags to Java programs. That is handled by libcore.
static constexpr uint32_t kAccValidInterfaceFlags = kAccPublic | kAccInterface |
                                                    kAccAbstract | kAccSynthetic | kAccAnnotation;

#endif //HOOK_ARTCONSTS_H_H
