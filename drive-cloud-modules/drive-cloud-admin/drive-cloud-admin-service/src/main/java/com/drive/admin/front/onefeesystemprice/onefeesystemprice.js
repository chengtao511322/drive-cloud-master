import request from '@/utils/request'


// 分页查询学车一费制定价表列表
export function listPageOneFeeSystemPrice(query) {
  return request({
    url: '/admin/oneFeeSystemPrice/pageList',
    method: 'post',
    params: query
  })
}


// 分页查询学车一费制定价表列表
export function listOneFeeSystemPrice(query) {
  return request({
    url: '/admin/oneFeeSystemPrice/findList',
    method: 'post',
    params: query
  })
}

// 查询学车一费制定价表详细
export function getOneFeeSystemPrice(id) {
  return request({
    url: '/admin/oneFeeSystemPrice/' + id,
    method: 'get'
  })
}

// 新增学车一费制定价表
export function addOneFeeSystemPrice(data) {
  return request({
    url: '/admin/oneFeeSystemPrice',
    method: 'post',
    data: data
  })
}

// 修改学车一费制定价表
export function updateOneFeeSystemPrice(data) {
  return request({
    url: '/admin/oneFeeSystemPrice',
    method: 'put',
    data: data
  })
}

// 删除学车一费制定价表
export function delOneFeeSystemPrice(id) {
  return request({
    url: '/admin/oneFeeSystemPrice/' + id,
    method: 'delete'
  })
}


// 状态启用/停用学车一费制定价表
export function changeStatus(data) {
  return request({
    url: '/admin/oneFeeSystemPrice/changeStatus',
    method: 'post',
    data: data
  })
}

// 导出学车一费制定价表
export function exportOneFeeSystemPrice(query) {
  return request({
    url: '/admin/oneFeeSystemPrice/exportXls',
    method: 'post',
    params: query,
    responseType: 'blob'
  })
}