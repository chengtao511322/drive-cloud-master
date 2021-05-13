import request from '@/utils/request'

                              
// 分页查询一费制学员升班价格控制表列表
export function listPageOneFeeSystemUpgradeClassPrice(query) {
  return request({
    url: '/admin/oneFeeSystemUpgradeClassPrice/pageList',
    method: 'post',
    data: query
  })
}


// 分页查询一费制学员升班价格控制表列表
export function listOneFeeSystemUpgradeClassPrice(query) {
  return request({
    url: '/admin/oneFeeSystemUpgradeClassPrice/findList',
    method: 'post',
    data: query
  })
}

// 查询一费制学员升班价格控制表详细
export function getOneFeeSystemUpgradeClassPrice(id) {
  return request({
    url: '/admin/oneFeeSystemUpgradeClassPrice/' + id,
    method: 'get'
  })
}

// 新增一费制学员升班价格控制表
export function addOneFeeSystemUpgradeClassPrice(data) {
  return request({
    url: '/admin/oneFeeSystemUpgradeClassPrice',
    method: 'post',
    data: data
  })
}

// 修改一费制学员升班价格控制表
export function updateOneFeeSystemUpgradeClassPrice(data) {
  return request({
    url: '/admin/oneFeeSystemUpgradeClassPrice',
    method: 'put',
    data: data
  })
}

// 删除一费制学员升班价格控制表
export function delOneFeeSystemUpgradeClassPrice(id) {
  return request({
    url: '/admin/oneFeeSystemUpgradeClassPrice/' + id,
    method: 'delete'
  })
}


// 状态启用/停用一费制学员升班价格控制表
export function changeStatus(data) {
  return request({
    url: '/admin/oneFeeSystemUpgradeClassPrice/changeStatus',
    method: 'post',
    data: data
  })
}

// 导出一费制学员升班价格控制表
export function exportOneFeeSystemUpgradeClassPrice(query) {
  return request({
    url: '/admin/oneFeeSystemUpgradeClassPrice/exportXls',
    method: 'post',
    params: query,
    responseType: 'blob'
  })
}